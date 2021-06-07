package com.gmail.timatiblackstar666.SpringMVC.controller;

import com.gmail.timatiblackstar666.SpringMVC.exceptions.DocumentNotFoundException;
import com.gmail.timatiblackstar666.SpringMVC.exceptions.UserNotFoundException;
import com.gmail.timatiblackstar666.SpringMVC.models.Document;
import com.gmail.timatiblackstar666.SpringMVC.models.DocumentType;
import com.gmail.timatiblackstar666.SpringMVC.models.User;
import com.gmail.timatiblackstar666.SpringMVC.services.IDocumentService;
import com.gmail.timatiblackstar666.SpringMVC.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDocumentService documentService;

    @GetMapping("")
    public String userPage(@RequestParam(name = "id", required = true)String id, Model model){
        User user = null;
        boolean isCurrentUser = false;
        try {
            user = userService.findUserById(id);
            isCurrentUser = user == userService.getCurrentUser();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("user", user);
        model.addAttribute("currentUser", isCurrentUser );
        return "user";
    }

    @GetMapping("new-document")
    public String addNewDocument(@RequestParam(name = "userId", required = true)String userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("newDocument", new Document());
        model.addAttribute("documentTypes", DocumentType.values());
        return "createDocument";
    }

    @PostMapping("new-document")
    public String saveNewDocument(@RequestParam(name = "userId", required = true)String userId,
                                 @ModelAttribute("newDocument") Document document,Model model){
        User user = null;
        try {
            user = userService.findUserById(userId);
            user.addToDocuments(document);
            documentService.saveDocument(document);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/user?id="+userId;
    }

    @GetMapping("delete-document")
    public String deleteDocument(@RequestParam(name = "docId") String docId,
                                 @RequestParam(name = "userId", required = false) String userId){
        if (!isRelyCurrentUser(userId)){
            return "redirect:/home";
        }
        try {
            documentService.deleteDocumentById(docId);
        } catch (DocumentNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/user?id="+userId;
    }

    @GetMapping("edit-document")
    public String editDocument(@RequestParam(name = "docId") String docId,
                               @RequestParam(name = "userId", required = false) String userId,
                               Model model){
        if (!isRelyCurrentUser(userId)){
            return "redirect:/home";
        }
        try {
            model.addAttribute("doc", documentService.findDocumentById(docId));
            model.addAttribute("userId", userId);
        } catch (DocumentNotFoundException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }
        return "editDocument";
    }

    private boolean isRelyCurrentUser(String userId){
        try{
            return (userId != null &&
                    userService.getCurrentUser().getId().equals(Long.parseLong(userId)));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
