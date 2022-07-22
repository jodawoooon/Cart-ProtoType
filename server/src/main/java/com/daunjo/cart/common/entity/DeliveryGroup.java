package com.daunjo.cart.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 배송 그룹 정보 entity
 */
@Entity
@Table(name="delivery_group")
public class DeliveryGroup {

    @Id
    @Column(name="group_cd", length = 10)
    private String groupCd;

    @Column(name="group_nm", nullable = false)
    private String groupNm;
}
