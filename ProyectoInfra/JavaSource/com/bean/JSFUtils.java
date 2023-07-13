package com.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtils {
    public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
