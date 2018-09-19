package com.youcai.user.dataobject;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pay {

    @Id
    private Integer id;

    private String name;

    private String state;
}
