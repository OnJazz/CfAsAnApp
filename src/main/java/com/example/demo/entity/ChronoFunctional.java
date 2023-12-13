package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ChronoFunctional")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChronoFunctional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Lob
    @Column(name = "fileData")
    private byte[] fileData;
}
