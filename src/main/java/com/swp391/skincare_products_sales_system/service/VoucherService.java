package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.VoucherCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.VoucherPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.VoucherResponse;

public interface VoucherService {
    VoucherResponse createVoucher(VoucherCreationRequest request);
    void deleteVoucher(Long voucherId);
    VoucherPageResponse getVouchersByAdmin(int page, int size);
    void exchangeVoucher(Long voucherId);
    VoucherPageResponse getVoucherByCustomer(int page, int size);
    VoucherPageResponse getMyVouchers(int page, int size);

    VoucherResponse getVoucher(Long id);
}
