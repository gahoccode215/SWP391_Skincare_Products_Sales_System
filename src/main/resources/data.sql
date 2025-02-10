-- Ensure Brand Table Exists
INSERT INTO tbl_brand (id, name, description, created_at, updated_at, is_deleted)
SELECT 1, 'L''Oreal', 'Thương hiệu mỹ phẩm hàng đầu thế giới', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_brand WHERE id = 1);
INSERT INTO tbl_brand (id, name, description, created_at, updated_at, is_deleted)
SELECT 2, 'The Ordinary', 'Chuyên về serum và dưỡng chất da', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_brand WHERE id = 2);
INSERT INTO tbl_brand (id, name, description, created_at, updated_at, is_deleted)
SELECT 3, 'Innisfree', 'Thương hiệu mỹ phẩm thiên nhiên từ Hàn Quốc', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_brand WHERE id = 3);
INSERT INTO tbl_brand (id, name, description, created_at, updated_at, is_deleted)
SELECT 4, 'Estée Lauder', 'Thương hiệu cao cấp chuyên chăm sóc sắc đẹp', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_brand WHERE id = 4);
INSERT INTO tbl_brand (id, name, description, created_at, updated_at, is_deleted)
SELECT 5, 'Clinique', 'Thương hiệu mỹ phẩm nổi tiếng về các sản phẩm chăm sóc da', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_brand WHERE id = 5);

-- Insert Categories (Ensure Unique UUIDs)
INSERT INTO tbl_category (id, name, slug, description, thumbnail, created_at, updated_at, is_deleted)
VALUES
    (UUID(), 'Serum', 'serum', 'Sản phẩm dưỡng da dạng serum', 'serum.jpg', NOW(), NOW(), 0),
    (UUID(), 'Kem dưỡng', 'kem-duong', 'Dưỡng ẩm cho da mặt', 'kem_duong.jpg', NOW(), NOW(), 0),
    (UUID(), 'Sữa rửa mặt', 'sua-rua-mat', 'Làm sạch da hàng ngày', 'sua_rua_mat.jpg', NOW(), NOW(), 0);
INSERT INTO tbl_category (id, name, slug, description, thumbnail, created_at, updated_at, is_deleted)
VALUES
    (UUID(), 'Mặt nạ', 'mat-na', 'Mặt nạ chăm sóc da', 'mat_na.jpg', NOW(), NOW(), 0),
    (UUID(), 'Dầu gội', 'dau-goi', 'Chăm sóc tóc với các sản phẩm dầu gội', 'dau_goi.jpg', NOW(), NOW(), 0);

-- Insert Origins (Avoid Duplicate Entries)
INSERT INTO tbl_origin (id, name, created_at, updated_at, is_deleted)
SELECT 1, 'Hàn Quốc', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_origin WHERE id = 1);
INSERT INTO tbl_origin (id, name, created_at, updated_at, is_deleted)
SELECT 2, 'Pháp', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_origin WHERE id = 2);
INSERT INTO tbl_origin (id, name, created_at, updated_at, is_deleted)
SELECT 3, 'Mỹ', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_origin WHERE id = 3);
INSERT INTO tbl_origin (id, name, created_at, updated_at, is_deleted)
SELECT 4, 'Nhật Bản', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_origin WHERE id = 4);
INSERT INTO tbl_origin (id, name, created_at, updated_at, is_deleted)
SELECT 5, 'Hà Lan', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_origin WHERE id = 5);

-- Insert Features (Ensure No Duplicates)
INSERT INTO tbl_feature (id, name, created_at, updated_at, is_deleted)
SELECT 1, 'Dưỡng ẩm', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_feature WHERE id = 1);
INSERT INTO tbl_feature (id, name, created_at, updated_at, is_deleted)
SELECT 2, 'Trị mụn', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_feature WHERE id = 2);
INSERT INTO tbl_feature (id, name, created_at, updated_at, is_deleted)
SELECT 3, 'Chống lão hóa', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_feature WHERE id = 3);
INSERT INTO tbl_feature (id, name, created_at, updated_at, is_deleted)
SELECT 4, 'Làm sáng da', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_feature WHERE id = 4);
INSERT INTO tbl_feature (id, name, created_at, updated_at, is_deleted)
SELECT 5, 'Chống nắng', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_feature WHERE id = 5);
INSERT INTO tbl_feature (id, name, created_at, updated_at, is_deleted)
SELECT 6, 'Dưỡng trắng', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_feature WHERE id = 6);
-- Ensure Skin Types are inserted correctly
INSERT INTO tbl_skin_type (id, type, created_at, updated_at, is_deleted)
SELECT 1, 'Da dầu', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_skin_type WHERE id = 1);

INSERT INTO tbl_skin_type (id, type, created_at, updated_at, is_deleted)
SELECT 2, 'Da khô', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_skin_type WHERE id = 2);
INSERT INTO tbl_skin_type (id, type, created_at, updated_at, is_deleted)
SELECT 3, 'Da nhạy cảm', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_skin_type WHERE id = 3);
INSERT INTO tbl_skin_type (id, type, created_at, updated_at, is_deleted)
SELECT 4, 'Da hỗn hợp', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_skin_type WHERE id = 4);
INSERT INTO tbl_skin_type (id, type, created_at, updated_at, is_deleted)
SELECT 5, 'Da mụn', NOW(), NOW(), 0
    WHERE NOT EXISTS (SELECT 1 FROM tbl_skin_type WHERE id = 5);

-- Insert Product
INSERT INTO tbl_product (id, name, price, slug, quantity_per_pack, product_code, description, thumbnail, usage_instruction, expiry_date, brand_id, origin_id, skin_type_id, category_id, created_at, updated_at, is_deleted)
VALUES
    (UUID(), 'Serum Vitamin C', 299000, 'serum-vitamin-c', 1, 'SP001', 'Dưỡng sáng da, chống oxy hóa', 'serum_vitc.jpg', 'Dùng mỗi tối trước khi đi ngủ', '2025-12-31', 1, 1, 2, (SELECT id FROM tbl_category WHERE slug='serum'), NOW(), NOW(), 0),
    (UUID(), 'Kem dưỡng ẩm Innisfree', 350000, 'kem-duong-am-innisfree', 1, 'SP002', 'Cấp ẩm tức thì cho da khô', 'kem_duong_am.jpg', 'Thoa đều lên mặt sáng và tối', '2026-06-30', 3, 1, 2, (SELECT id FROM tbl_category WHERE slug='kem-duong'), NOW(), NOW(), 0),
    (UUID(), 'Sữa rửa mặt The Ordinary', 250000, 'sua-rua-mat-the-ordinary', 1, 'SP003', 'Làm sạch da nhẹ nhàng, không gây kích ứng', 'sua_rua_mat.jpg', 'Sử dụng sáng và tối', '2025-09-15', 2, 2, 1, (SELECT id FROM tbl_category WHERE slug='sua-rua-mat'), NOW(), NOW(), 0),
    (UUID(), 'Kem chống nắng SunBlock', 500000, 'kem-chong-nang-sunblock', 1, 'SP004', 'Chống tia UV, bảo vệ da khỏi ánh nắng', 'kem_chong_nang.jpg', 'Thoa đều trước khi ra ngoài', '2026-12-31', 4, 1, 2, (SELECT id FROM tbl_category WHERE slug='mat-na'), NOW(), NOW(), 0),
    (UUID(), 'Dầu gội xả Dove', 150000, 'dau-goi-xa-dove', 1, 'SP005', 'Dầu gội dưỡng tóc mềm mượt', 'dau_goi_xa.jpg', 'Dùng mỗi ngày để tóc mềm mại', '2025-06-30', 5, 2, 1, (SELECT id FROM tbl_category WHERE slug='dau-goi'), NOW(), NOW(), 0);

-- Insert Product-Feature Relations
INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='serum-vitamin-c'), 4
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='serum-vitamin-c') AND feature_id = 4);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='kem-duong-am-innisfree'), 1
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='kem-duong-am-innisfree') AND feature_id = 1);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='sua-rua-mat-the-ordinary'), 2
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='sua-rua-mat-the-ordinary') AND feature_id = 2);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='kem-chong-nang-sunblock'), 5
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='kem-chong-nang-sunblock') AND feature_id = 5);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='dau-goi-xa-dove'), 6
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='dau-goi-xa-dove') AND feature_id = 6);
-- Insert additional products
INSERT INTO tbl_product (id, name, price, slug, quantity_per_pack, product_code, description, thumbnail, usage_instruction, expiry_date, brand_id, origin_id, skin_type_id, category_id, created_at, updated_at, is_deleted)
VALUES
    (UUID(), 'Tinh chất HA L\'Oreal', 450000, 'tinh-chat-ha-loreal', 1, 'SP006', 'Dưỡng ẩm sâu, giúp da căng mịn', 'tinh_chat_ha.jpg', 'Dùng sau toner, trước kem dưỡng', '2026-10-01', 2, 1, 2, (SELECT id FROM tbl_category WHERE slug='tinh-chat'), NOW(), NOW(), 0),
    (UUID(), 'Nước tẩy trang Garnier', 220000, 'nuoc-tay-trang-garnier', 1, 'SP007', 'Làm sạch lớp trang điểm, không chứa cồn', 'nuoc_tay_trang.jpg', 'Thấm vào bông tẩy trang, lau nhẹ nhàng', '2025-11-15', 3, 2, 1, (SELECT id FROM tbl_category WHERE slug='tay-trang'), NOW(), NOW(), 0),
    (UUID(), 'Toner hoa cúc Kiehl\'s', 700000, 'toner-hoa-cuc-kiehls', 1, 'SP008', 'Làm dịu da, cân bằng độ pH', 'toner_hoa_cuc.jpg', 'Dùng sau khi rửa mặt, trước serum', '2026-03-30', 4, 1, 2, (SELECT id FROM tbl_category WHERE slug='toner'), NOW(), NOW(), 0),
    (UUID(), 'Kem mắt Olay', 650000, 'kem-mat-olay', 1, 'SP009', 'Giảm quầng thâm, nếp nhăn quanh mắt', 'kem_mat_olay.jpg', 'Thoa nhẹ vùng mắt mỗi tối', '2026-09-15', 5, 1, 2, (SELECT id FROM tbl_category WHERE slug='kem-mat'), NOW(), NOW(), 0),
    (UUID(), 'Sữa dưỡng thể Vaseline', 180000, 'sua-duong-the-vaseline', 1, 'SP010', 'Dưỡng ẩm cơ thể, làm mềm da', 'sua_duong_the.jpg', 'Thoa đều sau khi tắm', '2026-08-30', 1, 2, 1, (SELECT id FROM tbl_category WHERE slug='duong-the'), NOW(), NOW(), 0),
    (UUID(), 'Tẩy tế bào chết St.Ives', 140000, 'tay-te-bao-chet-stives', 1, 'SP011', 'Làm sạch da chết, sáng da', 'tay_te_bao_chet.jpg', 'Massage nhẹ nhàng, rửa sạch với nước', '2025-12-01', 2, 2, 1, (SELECT id FROM tbl_category WHERE slug='tay-te-bao-chet'), NOW(), NOW(), 0),
    (UUID(), 'Dầu dưỡng tóc Moroccanoil', 900000, 'dau-duong-toc-moroccanoil', 1, 'SP012', 'Nuôi dưỡng tóc bóng khỏe, giảm xơ rối', 'dau_duong_toc.jpg', 'Thoa lên tóc khô hoặc ẩm', '2027-01-15', 3, 1, 2, (SELECT id FROM tbl_category WHERE slug='dau-goi'), NOW(), NOW(), 0),
    (UUID(), 'Xịt khoáng Avène', 320000, 'xit-khoang-avene', 1, 'SP013', 'Làm dịu da, cấp ẩm tức thì', 'xit_khoang.jpg', 'Xịt đều lên mặt khi cần cấp ẩm', '2026-06-10', 4, 2, 2, (SELECT id FROM tbl_category WHERE slug='xit-khoang'), NOW(), NOW(), 0),
    (UUID(), 'Son dưỡng môi DHC', 200000, 'son-duong-moi-dhc', 1, 'SP014', 'Giữ ẩm môi, giảm khô nứt', 'son_duong_moi.jpg', 'Thoa đều lên môi khi cần', '2025-10-20', 5, 1, 1, (SELECT id FROM tbl_category WHERE slug='son-duong'), NOW(), NOW(), 0),
    (UUID(), 'Kem trị mụn La Roche-Posay', 480000, 'kem-tri-mun-la-roche-posay', 1, 'SP015', 'Hỗ trợ giảm mụn, kháng khuẩn', 'kem_tri_mun.jpg', 'Thoa lên vùng da bị mụn', '2026-05-25', 1, 2, 1, (SELECT id FROM tbl_category WHERE slug='tri-mun'), NOW(), NOW(), 0);

-- Insert Product-Feature Relations for new products
INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='tinh-chat-ha-loreal'), 3
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='tinh-chat-ha-loreal') AND feature_id = 3);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='nuoc-tay-trang-garnier'), 1
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='nuoc-tay-trang-garnier') AND feature_id = 1);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='toner-hoa-cuc-kiehls'), 2
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='toner-hoa-cuc-kiehls') AND feature_id = 2);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='kem-mat-olay'), 5
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='kem-mat-olay') AND feature_id = 5);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='sua-duong-the-vaseline'), 6
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='sua-duong-the-vaseline') AND feature_id = 6);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='tay-te-bao-chet-stives'), 4
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='tay-te-bao-chet-stives') AND feature_id = 4);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='dau-duong-toc-moroccanoil'), 3
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='dau-duong-toc-moroccanoil') AND feature_id = 3);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='xit-khoang-avene'), 1
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='xit-khoang-avene') AND feature_id = 1);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='son-duong-moi-dhc'), 2
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='son-duong-moi-dhc') AND feature_id = 2);

INSERT INTO tbl_product_has_feature (product_id, feature_id)
SELECT (SELECT id FROM tbl_product WHERE slug='kem-tri-mun-la-roche-posay'), 5
    WHERE NOT EXISTS (SELECT 1 FROM tbl_product_has_feature WHERE product_id = (SELECT id FROM tbl_product WHERE slug='kem-tri-mun-la-roche-posay') AND feature_id = 5);
