package com.lzqstudy.bean;

import lombok.Data;

/**
 * @author 李正强
 * @version 1.0
 */
@Data
public class Chat {
    private String model;
    private Message[] messages;
}
