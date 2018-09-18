package com.itvilla.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author imssbora
 */
@Controller
public class FileUploadController {

   @GetMapping("/")
   public String fileUploadForm(Model model) {

      return "index";
   }

   // Handling single file upload request
   @PostMapping("/singleFileUpload")
   public String singleFileUpload(@RequestParam("file") MultipartFile file, Model model)
         throws IOException {
	   System.out.println("in single file upload hanldoer");
      // Save file on system
      if (!file.getOriginalFilename().isEmpty()) {
    	  
    	  System.out.println(" in uploading file method");
    	  System.out.println("Name of the file " + file.getOriginalFilename());
    	  System.out.println("lets see hwat here in content type" + file.getContentType());
    	  System.out.println("lets gett he size ofthe file " + file.getSize());
    	  System.out.println("lets get the class" + file.getClass());
    	  
         BufferedOutputStream outputStream = new BufferedOutputStream(
               new FileOutputStream(
                     new File("/home/ec2-user/itvilla/images", file.getOriginalFilename())));
         outputStream.write(file.getBytes());
         outputStream.flush();
         outputStream.close();

         model.addAttribute("msg", "File uploaded successfully.");
      } else {
         model.addAttribute("msg", "Please select a valid file..");
      }

      return "index";
   }

   // Handling multiple files upload request
    @PostMapping("/multipleFileUpload")
   public String multipleFileUpload(@RequestParam("file") MultipartFile[] files,
         Model model) throws IOException {

      // Save file on system
      for (MultipartFile file : files) {
         if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                  new FileOutputStream(
                        new File("C:/Temp", file.getOriginalFilename())));

            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
         } else {
            model.addAttribute("msg", "Please select at least one file..");
            return "index";
         }
      }
      model.addAttribute("msg", "Multiple files uploaded successfully.");
      return "index";
   } 
}
