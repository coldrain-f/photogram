package com.cos.controllerdemo.repository;

import com.cos.controllerdemo.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
