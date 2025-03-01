package com.swp391.skincare_products_sales_system.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserPageResponse extends AbstractPageResponse implements Serializable {

    private List<UserResponse> userResponses;
}
