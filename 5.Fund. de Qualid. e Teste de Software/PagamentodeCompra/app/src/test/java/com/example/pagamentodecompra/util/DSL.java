package com.example.pagamentodecompra.util;

import static com.example.pagamentodecompra.util.DriverFactory.getDriver;

import org.openqa.selenium.By;

public class DSL {
    public void clicar (By by){
        getDriver().findElement(by).click();
    }

    public String valorTotal (By by){
        return getDriver().findElement(by).getText();
    }

    public void selecionarItens(String item){
        if(item.equals("Arroz (R$ 3,50)")){
            DriverFactory.getDriver().findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                    "android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.RadioGroup/android.widget.CheckBox[1]").click();
        }
        else if(item.equals("Carne (R$ 11,30)")){
            DriverFactory.getDriver().findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                    "android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.RadioGroup/android.widget.CheckBox[2]").click();
        }
        else if(item.equals("Pão (R$ 2,20)")){
            DriverFactory.getDriver().findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                    "android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.RadioGroup/android.widget.CheckBox[3]").click();
        }
        else if(item.equals("Leite (R$ 5,50)")){
            DriverFactory.getDriver().findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                    "android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.RadioGroup/android.widget.CheckBox[4]").click();
        }
        else if(item.equals("Ovos (R$ 7,50)")){
            DriverFactory.getDriver().findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                    "android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.RadioGroup/android.widget.CheckBox[5]").click();
        }
    }
}
