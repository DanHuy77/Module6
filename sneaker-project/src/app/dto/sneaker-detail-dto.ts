import {Customer} from '../model/customer';

export interface SneakerDetailDto {
  size?: string;
  url?: string;
  sneakerName?: string;
  price?: number;
  productCode?: string;
  color?: string;
  detailId?: number;
  subTotal?: number;
  inCartQuantity?: number;
  sneakerId?: number;
  deliveryAddress?: string;
  deliveryPhoneNumber?: string;
  totalValue?: number;
  customer?: Customer;
}
