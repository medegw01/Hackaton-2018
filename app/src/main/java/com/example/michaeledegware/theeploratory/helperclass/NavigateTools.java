package com.example.michaeledegware.theeploratory.helperclass;

/**
 * Created by Michael on 2/9/2018.
 */
public class NavigateTools {
    private String toolName;
    private int resIcon;

    public NavigateTools(String toolName,int resIcon) {
        this.toolName = toolName;
        this.resIcon = resIcon;
    }

    public String getToolName() {
        return toolName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

}
