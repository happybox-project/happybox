package com.app.happybox.entity.customer;

import lombok.Data;

@Data
public class NoticeSearch {
    private String searchType;
    private String keyword;
    private String noticeWhole;
    private String noticeTitle;
    private String noticeContent;
}
