//package com.javaweb.crawl;
//
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.javaweb.repository.entity.BrandEntity;
//import com.javaweb.repository.entity.LaptopModelEntity;
//import com.javaweb.repository.entity.CpuEntity;
//import com.javaweb.repository.entity.RamEntity;
//import com.javaweb.repository.entity.GpuEntity;
//import com.javaweb.repository.entity.StorageEntity;
//import com.javaweb.repository.entity.DisplayEntity;
//import com.javaweb.repository.entity.PowerEntity;
//import com.javaweb.repository.entity.ConnectivityEntity;
//import com.javaweb.repository.entity.RatingEntity;
//import com.javaweb.repository.entity.LaptopConfigurationEntity;
//import com.javaweb.repository.entity.ReviewsEntity;
//
//import java.io.File;
//import java.util.*;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
//@Component
//public class JsonToDbRunner implements CommandLineRunner {
//
//    private final NamedParameterJdbcTemplate jdbc;
//
//    public JsonToDbRunner(JdbcTemplate template) {
//        this.jdbc = new NamedParameterJdbcTemplate(template);
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//    	System.out.println("🚀 Đã vào JsonToDbRunner");
//
//    	String path = "C:/Users/Admin/Downloads/laptops_page17 (1).json"; 
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<Map<String,Object>> laptops = mapper.readValue(
//            new File(path),
//            new TypeReference<List<Map<String,Object>>>(){}
//        );
//        System.out.println("📦 Đã load " + laptops.size() + " laptop từ JSON");
//
//        for (Map<String,Object> dto : laptops) {
//        	System.out.println("➡ Đang xử lý model: " + dto.get("model") + " | Brand: " + dto.get("brand"));
//        	 String model = dto.get("model") != null ? dto.get("model").toString().trim() : "";
//        	    if (model.isEmpty() || model.equalsIgnoreCase("rỗ") || model.length() < 4) {
//        	        System.err.println("⛔ Bỏ qua model không hợp lệ: " + model);
//        	        continue; // bỏ qua laptop này
//        	    }
//
//        	    // ✅ Kiểm tra brand
//        	    String brand = dto.get("brand") != null ? dto.get("brand").toString().trim() : "";
//        	    if (brand.isEmpty() || brand.equalsIgnoreCase("không") || brand.length() < 2) {
//        	        System.err.println("⛔ Bỏ qua brand không hợp lệ: " + brand);
//        	        continue; // hoặc thay bằng brand = "Unknown";
//        	    }
//
//        	    // 🟢 Nếu cần xử lý lại brand trước khi insert:
//        	    dto.put("model", model); // đảm bảo model được làm sạch
//        	    dto.put("brand", brand); // đảm bảo brand được làm sạch
//
//
//            // Brand
//            Integer brandId = getOrInsert("Brand",
//                Collections.singletonMap("name", dto.get("brand")),
//                Collections.singletonList("id")
//            );
//
//            // LaptopModel
//            Map<String,Object> modelParams = new HashMap<>();
//            modelParams.put("model_name", dto.get("model"));
//            modelParams.put("brand_id", brandId);
//            modelParams.put("description", dto.get("url"));
//            Integer modelId = getOrInsert("LaptopModel", modelParams, Collections.singletonList("id"));
//
//            @SuppressWarnings("unchecked")
//            Map<String,String> specs = (Map<String,String>) dto.get("specifications");
//
//            // CPU
//            Integer cpuId = null;
//            if (specs.containsKey("Công nghệ CPU")) {
//                cpuId = getOrInsert("CPU",
//                    Collections.singletonMap("model", specs.get("Công nghệ CPU")),
//                    Collections.singletonList("id")
//                );
//            }
//
//
//            // RAM
//            Integer ramId = null;
//            if (specs.containsKey("RAM")) {
//                ramId = getOrInsert("RAM",
//                    Collections.singletonMap("capacity", parseInt(specs.get("RAM"))),
//                    Collections.singletonList("id")
//                );
//            }
//
//            // GPU
//            Integer gpuId = null;
//            if (specs.containsKey("Card màn hình")) {
//                gpuId = getOrInsert("GPU",
//                    Collections.singletonMap("name", specs.get("Card màn hình")),
//                    Collections.singletonList("id")
//                );
//            }
//
//            // Storage
//            Integer storageId = null;
//            if (specs.containsKey("Ổ cứng")) {
//                Map<String,Object> st = new HashMap<>();
//                st.put("type", specs.get("Ổ cứng"));
//                storageId = getOrInsert("Storage", st, Collections.singletonList("id"));
//            }
//
//         // Display
//            Integer displayId = null;
//            if (specs.containsKey("Độ phân giải") || specs.containsKey("Màn hình") || specs.containsKey("Tần số quét")) {
//                Map<String, Object> displayMap = new HashMap<>();
//
//                // Độ phân giải
//                if (specs.containsKey("Độ phân giải")) {
//                    displayMap.put("resolution", specs.get("Độ phân giải"));
//                }
//
//                // Kích thước màn hình (size)
//                if (specs.containsKey("Màn hình")) {
//                    String rawSize = specs.get("Màn hình").toString().replace("\"", "").trim();
//                    try {
//                        displayMap.put("size", Float.parseFloat(rawSize));
//                    } catch (NumberFormatException e) {
//                        System.err.println("⚠️ Không thể parse size màn hình: " + rawSize);
//                    }
//                }
//
//                // Tần số quét (refreshRate)
//                if (specs.containsKey("Tần số quét")) {
//                    String hzStr = specs.get("Tần số quét").toString().replaceAll("[^0-9]", "").trim();
//                    try {
//                        displayMap.put("refreshRate", Integer.parseInt(hzStr));
//                    } catch (NumberFormatException e) {
//                        System.err.println("⚠️ Không thể parse tần số quét: " + specs.get("Tần số quét"));
//                    }
//                }
//
//                // So sánh theo resolution + size + refreshRate để tránh trùng
//                displayId = getOrInsert("Display", displayMap, Arrays.asList("resolution", "size", "refreshRate"));
//            }
//
//
//
//
//            // Power
//            Integer powerId = null;
//            if (specs.containsKey("Thông tin Pin")) {
//                powerId = getOrInsert("Power",
//                    Collections.singletonMap("type", specs.get("Thông tin Pin")),
//                    Collections.singletonList("id")
//                );
//            }
//
//            // Connectivity
//            Integer connId = null;
//            if (specs.containsKey("Cổng giao tiếp")) {
//                connId = getOrInsert("Connectivity",
//                    Collections.singletonMap("port", specs.get("Cổng giao tiếp")),
//                    Collections.singletonList("id")
//                );
//            }
//
//            // Rating
//            Integer ratingId = null;
//            if (dto.get("rating") != null) {
//                Map<String,Object> r = new HashMap<>();
//                r.put("average", parseFloat(dto.get("rating").toString()));
//                ratingId = insertAndGetId("Rating", r);
//            }
//
//            // LaptopConfiguration
//            Map<String,Object> cfg = new HashMap<>();
//            cfg.put("model_id", modelId);
//            cfg.put("cpu_id", cpuId);
//            cfg.put("ram_id", ramId);
//            cfg.put("storage_id", storageId);
//            cfg.put("display_id", displayId);
//            cfg.put("gpu_id", gpuId);
//            cfg.put("power_id", powerId);
//            cfg.put("connectivity_id", connId);
//            cfg.put("rating_id", ratingId);
//            String rawPrice = dto.get("price").toString(); // "24.690.000₫ *"
//            rawPrice = rawPrice.replaceAll("[^\\d]", ""); // giữ lại số → "24690000"
//            Float price = null;
//            if (!rawPrice.isEmpty()) {
//                price = Float.parseFloat(rawPrice);
//            }
//            cfg.put("price", price);
//
//            cfg.put("specifications", specs.get("Kích thước"));
//            cfg.put("material", specs.get("Chất liệu"));
//            cfg.put("os", specs.get("Hệ điều hành"));
//            cfg.put("warranty", parseInt(specs.get("Thời gian bảo hành")));
//            cfg.put("color", dto.get("color"));
//            @SuppressWarnings("unchecked")
//            List<String> imgs = (List<String>) dto.get("images");
//            cfg.put("image_url", (imgs != null && !imgs.isEmpty()) ? imgs.get(0) : null);
//
//            Integer cfgId = insertAndGetId("LaptopConfiguration", cfg);
//
//            // Reviews
//            if (dto.get("reviews") != null) {
//                for (Map<String,String> rv : (List<Map<String,String>>) dto.get("reviews")) {
//                    Map<String,Object> rr = new HashMap<>();
//                    rr.put("configuration_id", cfgId);
//                    rr.put("user", rv.get("username"));
//                    rr.put("content", rv.get("comment"));
//                    rr.put("date", parseDate(rv.get("date")));
//                    insertAndGetId("Reviews", rr);
//                }
//            }
//        }
//    }
//
//    private Integer getOrInsert(String table, Map<String,Object> keys, List<String> out) {
//        String where = String.join(" AND ", keys.keySet().stream().map(k -> k + " = :" + k).toList());
//        String sql = "SELECT " + String.join(",", out) + " FROM " + table + " WHERE " + where;
//        try {
//            return jdbc.queryForObject(sql, keys, Integer.class);
//        } catch (Exception e) {
//            return insertAndGetId(table, keys);
//        }
//    }
//
//    private Integer insertAndGetId(String table, Map<String,Object> cols) {
//        SimpleJdbcInsert inj = new SimpleJdbcInsert(jdbc.getJdbcTemplate())
//                .withTableName(table)
//                .usingGeneratedKeyColumns("id");
//        return inj.executeAndReturnKey(new MapSqlParameterSource(cols)).intValue();
//    }
//
//    private Integer parseInt(String s) {
//        if (s == null) return null;
//        try { return Integer.parseInt(s.replaceAll("[^\\d]", "")); } catch (Exception e) { return null; }
//    }
//
//    private Float parseFloat(String s) {
//        if (s == null) return null;
//        try { return Float.parseFloat(s.replaceAll("[^\\d\\.]", "")); } catch (Exception e) { return null; }
//    }
//
//    private Date parseDate(String s) {
//        try {
//            var fmt = DateTimeFormatter.ofPattern("d/M/yyyy h:mm:ss a", Locale.ENGLISH);
//            LocalDate ld = LocalDate.parse(s, fmt);
//            return Date.valueOf(ld);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
