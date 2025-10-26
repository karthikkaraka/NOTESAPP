package com.karthik.NOTESAPP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loginresponse {
    private String token;
    private long userid;
    private String userName;
}
