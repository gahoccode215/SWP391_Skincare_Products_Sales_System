package com.swp391.skincare_products_sales_system.enums;

public enum SkinType {
    SENSITIVE_SKIN("Da nhạy cảm: Sử dụng các sản phẩm nhẹ nhàng, không chứa hương liệu."),
    DRY_SKIN("Da khô: Sử dụng kem dưỡng ẩm dày và tránh các sữa rửa mặt làm khô da."),
    COMBINATION_SKIN("Da hỗn hợp: Sử dụng sản phẩm cân bằng giữa vùng da dầu và khô."),
    OILY_SKIN("Da dầu: Sử dụng các sản phẩm không chứa dầu và tẩy tế bào chết thường xuyên."),
    NORMAL_SKIN("Da bình thường: Sử dụng các sản phẩm nhẹ nhàng để duy trì sự cân bằng.");

    private final String recommendation;

    // Constructor để khởi tạo khuyến nghị cho từng loại da
    SkinType(String recommendation) {
        this.recommendation = recommendation;
    }

    // Phương thức getter để lấy khuyến nghị
    public String getRecommendation() {
        return recommendation;
    }
}
