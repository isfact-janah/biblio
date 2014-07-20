package com.couchdb.biblio.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ManagedBean(name="fileUploadController")
@SessionScoped
public class FileUploadController {  

public void handleFileUpload(FileUploadEvent event) {  
    FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
    FacesContext.getCurrentInstance().addMessage(null, msg);  
     }  
}
