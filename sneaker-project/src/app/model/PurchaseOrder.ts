import {Customer} from './customer';

export interface PurchaseOrder {
deliveryAddress?: string;
deliveryPhoneNumber?: string;
paymentStatus?: boolean;
totalValue?: number;
customer?: Customer;
}
