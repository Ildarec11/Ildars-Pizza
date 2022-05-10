package models;

import lombok.Data;

@Data
public class BoughtDish {

    private Dish dish;
    private PurchaseHistoryModel purchaseHistoryModel;
}
