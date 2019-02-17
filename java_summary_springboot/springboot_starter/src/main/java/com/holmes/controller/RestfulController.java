package com.holmes.controller;

import com.alibaba.fastjson.JSON;
import com.holmes.entity.User;
import com.holmes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/user")
@RestController
public class RestfulController {

    @Autowired
    private UserService userService;

    /**
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getUserList() {

        List<User> list = userService.getAll();

        return JSON.toJSONString(list);
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable(value = "id") Integer id) {

        System.out.println("id:" + id);
        User user = userService.getUser(id);

        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody User user) {

        System.out.println(user);

        userService.addUser(user);

        return JSON.toJSONString(user);
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {

        try {
            System.out.println("id:" + id);
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody User user) {

        try {
            System.out.println(user);
            userService.update(user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
