package com.swp391.skincare_products_sales_system.controller.admin;

import com.swp391.skincare_products_sales_system.dto.request.ProductBatchRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.model.Batch;
import com.swp391.skincare_products_sales_system.service.ProductBatchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/batches")
@Tag(name = "Batch Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminProductBatchController {
    ProductBatchService productBatchService;

    @PostMapping("/import-product")
    public ApiResponse<Batch> importProductBatch(@RequestBody ProductBatchRequest request){
        return ApiResponse.<Batch>builder()
                .code(HttpStatus.OK.value())
                .message("Batch imported successfully")
                .result(productBatchService.importProductBatch(request))
                .build();
    }
}
