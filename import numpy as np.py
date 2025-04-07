import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import minimize

# -------------------------------
# 1. Dữ liệu
# -------------------------------
# Dữ liệu huấn luyện (0 đến 10 ngày)
days_train = np.array([0, 0.25, 0.5, 1, 2, 3, 4, 5, 6, 8, 10])
drug_train  = np.array([2.5, 3.6, 5.3, 9.5, 14.0, 16.5, 18.8, 21.5, 23.2, 26.8, 28.4])
# Dữ liệu hold-out (ngoài phạm vi huấn luyện: 12, 16, 21 ngày)
days_holdout = np.array([12, 16, 21])
drug_holdout  = np.array([28.4, 28.5, 29.5])

# -------------------------------
# 2. Hàm fit đa thức (polyfit/polyval)
# -------------------------------
def fit_poly_model(order, x, y):
    coeffs = np.polyfit(x, y, order)
    y_pred = np.polyval(coeffs, x)
    residuals = y - y_pred
    ssr = np.sum(residuals**2)
    return coeffs, y_pred, residuals, ssr

# Fit cho đa thức bậc 1, 2, 3
models = {}
orders = [1, 2, 3]
print("==== Fit đa thức ====")
for order in orders:
    coeffs, y_pred, residuals, ssr = fit_poly_model(order, days_train, drug_train)
    pred_holdout = np.polyval(coeffs, days_holdout)
    models[f'poly{order}'] = {
        'order': order,
        'coeffs': coeffs,
        'ssr': ssr,
        'pred_holdout': pred_holdout,
        'pred_train': y_pred
    }
    print(f"Đa thức bậc {order}:")
    print("  Hệ số:", coeffs)
    print("  SSR =", ssr)
    print("  Dự đoán hold-out:", pred_holdout)
    print()

# -------------------------------
# 3. Fit mô hình hàm mũ: Y(t) = C*(1 - exp(-a*t))
# -------------------------------
def exp_model(t, params):
    C, a = params
    return C * (1 - np.exp(-a * t))

def exp_ssr(params, t, y):
    return np.sum((y - exp_model(t, params))**2)

# Ước lượng ban đầu: C = 10, a = 2
initial_guess = [10, 2]
result = minimize(exp_ssr, initial_guess, args=(days_train, drug_train))
exp_params = result.x
ssr_exp = exp_ssr(exp_params, days_train, drug_train)
pred_holdout_exp = exp_model(days_holdout, exp_params)
models['exp'] = {
    'params': exp_params,
    'ssr': ssr_exp,
    'pred_holdout': pred_holdout_exp,
    'pred_train': exp_model(days_train, exp_params)
}
print("==== Mô hình hàm mũ ====")
print("Tham số (C, a):", exp_params)
print("SSR =", ssr_exp)
print("Dự đoán hold-out:", pred_holdout_exp)
print()

# -------------------------------
# 4. Vẽ đồ thị cho dữ liệu huấn luyện và hold-out (dự đoán ngoài)
# -------------------------------
# Tạo một mảng các giá trị x dày đặc cho vẽ đường mô hình (0 đến 22 ngày)
x_dense = np.linspace(0, 22, 300)

plt.figure(figsize=(10, 6))
plt.plot(days_train, drug_train, 'ko', label="Training Data")
plt.plot(days_holdout, drug_holdout, 'ks', markersize=8, label="Hold-out Data")

colors = {'poly1': 'r', 'poly2': 'g', 'poly3': 'b', 'exp': 'm'}
for key, model in models.items():
    if key.startswith('poly'):
        coeffs = model['coeffs']
        y_dense = np.polyval(coeffs, x_dense)
        order = model['order']
        plt.plot(x_dense, y_dense, color=colors[key],
                 label=f"Poly Order {order} (SSR={model['ssr']:.1f})")
        # Vẽ dự đoán hold-out
        plt.plot(days_holdout, np.polyval(coeffs, days_holdout), 'o', color=colors[key])
    else:  # exponential model
        y_dense = exp_model(x_dense, exp_params)
        plt.plot(x_dense, y_dense, color=colors['exp'],
                 label=f"Exponential (C={exp_params[0]:.2f}, a={exp_params[1]:.2f}, SSR={ssr_exp:.1f})")
        plt.plot(days_holdout, pred_holdout_exp, 'o', color=colors['exp'])

plt.xlabel("Day")
plt.ylabel("Drug Released (µg)")
plt.title("Fit các mô hình: Training & Hold-out")
plt.legend()
plt.grid(True)
plt.show()

# -------------------------------
# 5. Vẽ biểu đồ residuals cho đa thức bậc 1 (ví dụ)
# -------------------------------
coeffs1, y_pred1, residuals1, ssr1 = fit_poly_model(1, days_train, drug_train)
plt.figure()
plt.bar(days_train, residuals1, width=0.3, color='r')
plt.xlabel("Day")
plt.ylabel("Residual")
plt.title(f"Residuals của đa thức bậc 1 (SSR = {ssr1:.1f})")
plt.grid(True)
plt.show()

# -------------------------------
# 6. In kết quả SSR và tham số của các mô hình
# -------------------------------
print("==== Kết quả SSR & tham số (Training Data) ====")
for key, model in models.items():
    if key.startswith('poly'):
        print(f"Đa thức bậc {model['order']}:")
        print("  Hệ số:", model['coeffs'])
        print("  SSR =", model['ssr'])
        print("  Dự đoán hold-out:", model['pred_holdout'])
    else:
        print("Mô hình hàm mũ:")
        print("  Tham số (C, a):", model['params'])
        print("  SSR =", model['ssr'])
        print("  Dự đoán hold-out:", model['pred_holdout'])
    print()

# -------------------------------
# 7. Phân tích theo Occam's Razor (In ra nhận xét)
# -------------------------------
print("==== Occam's Razor Analysis ====")
print(" - Nếu ta ưu tiên số lượng tham số ít (đơn giản) và ý nghĩa vật lý, mô hình hàm mũ (2 tham số) là lựa chọn tốt.")
print(" - Nếu chỉ xét về độ khớp dữ liệu trên training (SSR thấp nhất), đa thức bậc 3 có thể cho kết quả tốt nhất,")
print("   nhưng có nguy cơ overfit và cho dự đoán ngoài phạm vi không ổn định.")
print("=> Do đó, nếu ta xem trọng khả năng dự đoán ổn định ngoài dữ liệu huấn luyện, mô hình hàm mũ là lựa chọn hợp lý.")
print()

# -------------------------------
# 8. Dự đoán ngoài phạm vi huấn luyện (10 đến 21 ngày)
# -------------------------------
# Tạo mảng thời gian từ 0 đến 22 (để bao gồm cả extrapolation)
x_full = np.linspace(0, 22, 300)

plt.figure(figsize=(10, 6))
plt.plot(days_train, drug_train, 'ko', label="Training Data")
plt.plot(days_holdout, drug_holdout, 'ks', markersize=8, label="Hold-out Data")
for key, model in models.items():
    if key.startswith('poly'):
        y_full = np.polyval(model['coeffs'], x_full)
        plt.plot(x_full, y_full, color=colors[key],
                 label=f"Poly Order {model['order']}")
    else:
        y_full = exp_model(x_full, exp_params)
        plt.plot(x_full, y_full, color=colors['exp'],
                 label="Exponential")
plt.xlabel("Day")
plt.ylabel("Drug Released (µg)")
plt.title("Dự đoán (Extrapolation) từ 0 đến 22 ngày")
plt.legend()
plt.grid(True)
plt.show()

print("==== Phân tích Dự đoán ngoài (Part 8) ====")
print(" - Khi dùng các mô hình để dự đoán từ 10 đến 21 ngày, các đa thức (đặc biệt bậc 1 và 2) thường cho dự đoán không ổn định.")
print(" - Đa thức bậc 3 có thể cho SSR tốt trên training nhưng extrapolation lại dao động hoặc không hợp lý.")
print(" - Mô hình hàm mũ, với đặc tính bão hòa, cho dự đoán ổn định và hợp lý trong khoảng 10-21 ngày.")
print("=> Vậy, dựa trên khả năng dự đoán ngoài (extrapolation), mô hình hàm mũ là lựa chọn tốt nhất.")
