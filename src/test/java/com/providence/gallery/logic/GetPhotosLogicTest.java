package com.providence.gallery.logic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetPhotosLogicTest {

    private GetPhotosLogic getPhotosLogic;

    @Test
    void updateDBFromNasTest(){
        getPhotosLogic.updateDBFromNas("./root");
    }
}
