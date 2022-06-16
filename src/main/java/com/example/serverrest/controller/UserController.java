package com.example.serverrest.controller;



import com.example.serverrest.models.ResponseForStatus;
import com.example.serverrest.models.User;
import com.example.serverrest.repository.Userrepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class UserController {
    @Autowired
    Userrepository userrepository;
    @Value("${upload.path}")
    private String pathFile;
    @RequestMapping(path = "/addpictur", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> addUser(@RequestPart(name = "file",required = false)MultipartFile multipartFile,
                                             @RequestPart(name = "user",required = false) String user) throws IOException {

        if (multipartFile!=null && user != null){
            User user1 = new Gson().fromJson(user, User.class);
            String filename= multipartFile.getOriginalFilename();
            user1.setPictureUrl(multipartFile.getName());
            user1.setTimestamp(new Timestamp(new Date().getTime()));
            String a=  RandomString.make()+ filename ;
            String b =  pathFile + "/" + a;
            File file = new File(b);
            multipartFile.transferTo(file);
            userrepository.save(user1);
            return new ResponseEntity<String>(String.valueOf(user1.getId()),HttpStatus.OK);
        }
        else if (multipartFile == null){
            return new ResponseEntity<String>("нету файла",HttpStatus.NOT_FOUND);
        }
        else if (user == null){
            return new ResponseEntity<String>("нет юзера", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/{id}")
        public Callable<ResponseEntity<ResponseForStatus>>statusUser(@RequestParam(name = "current") Boolean current,
                                                                     @PathVariable Long id) throws InterruptedException {
       return ()-> { // лайфхак чтобы не переопределять весь интерфейс
           Date date = new Date();
           TimeUnit.SECONDS.sleep((int) (Math.random() * 5 + 5));// заглушка на рандом 5-10 секунд
           if (userrepository.existsById(id)) {
               User user = userrepository.findById(id).get();
               boolean oldStatus = user.isStatus();
               user.setStatus(current);
               user.setTimestamp(new Timestamp(date.getTime()));
               userrepository.save(user);
               ResponseForStatus responseForStatus = new ResponseForStatus(id, oldStatus, current);
               return new ResponseEntity<ResponseForStatus>(responseForStatus, HttpStatus.OK);
           }
           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
       };
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> viewUser(@PathVariable Long id){

     //  if (userrepository.findById(id).get()!=null){
     //  }
        if (userrepository.existsById(id)){// через функцию boolean в репозитории находим по ID
            User user1= userrepository.findById(id).get();
            return new ResponseEntity<>(user1,HttpStatus.OK);
    }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Статистика сервера.
    //Передаем параметры на сервер: 1. статус клиентов (Online, Offline или отсутствует), 2. уникальный ID (timestamp) запроса (может отсутствовать)
    //Ответ сервера - список пользователей со статусами и URI картинки, а также уникальный ID (timestamp) запроса.
    //Примечание: Если в запросе есть параметры, то сервер должен фильтровать по ним свой ответ.
    // Если в запросе есть уникальный ID (timestamp) запроса (полученный ранее), то сервер должен вернуть только пользователей, у которых изменились статусы после (по времени)этого уникального ID (timestamp).


    @GetMapping("/all")
    public ResponseEntity<List<User>>  statistics( @RequestParam(name = "id", required = false)String id ,
                                                   @RequestParam(name = "status",required = false)Boolean status){
        if (id !=null && status !=null){
           List<User>userList= userrepository.findAllByStatusAndIdAfter(status,Long.parseLong(id));
           return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
        }
        else if (id != null){
            List<User> userList= userrepository.findAllByIdIsAfter(Long.parseLong(id));
            return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
        }
        else if (status != null){
          List<User>userList= userrepository.findAllByStatus(status);
            return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }






    //Статистика сервера.@RequestParam(name = "current",required = false)
    // это вывод чуваков в зависимости от параметров

}
