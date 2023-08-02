package com.example.demo.form;

import lombok.Data;

/**
 * ユーザーからの入力値を保持するためのクラス
 * 
 */
@Data
public class BookForm {
	
	/**
     * 本のタイトルを保持する
     */
    private String title;

    /**
     * 本の値段を保持する
     */
    private Integer price;

}
