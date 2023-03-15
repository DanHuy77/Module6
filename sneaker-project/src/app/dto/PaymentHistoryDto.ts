export interface PaymentHistoryDto {
  purchaseOrderId?: number;

  deliveryAddress?: string;

  orderDate?: string;

  phoneNumber: string;

  totalValue: number;

  customerName: string;
}
