package com.backend.vegap.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedicDTO {

    @EqualsAndHashCode.Include
    private Integer idMedic;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Size(max = 12)
    @NotNull
    private String cmp;

    @NotNull
    private String photoUrl;

}
