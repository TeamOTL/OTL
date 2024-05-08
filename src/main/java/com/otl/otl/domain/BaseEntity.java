
package com.otl.otl.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "board_createDate", updatable = false)
    private LocalDateTime regDate;      // 생성일

    @LastModifiedDate
    @Column(name = "board_modDate")
    private LocalDateTime modDate;      // 수정일

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public LocalDateTime getModDate() {
        return modDate;
    }
}


