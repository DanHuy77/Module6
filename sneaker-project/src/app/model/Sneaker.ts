import {Category} from './Category';
import {SneakerDetailList} from './Sneaker-detail-list';

export interface Sneaker {
  sneakerId?: number;
  sneakerName?: string;
  descriptionTitle?: string;
  description?: string;
  productCode?: string;
  producer?: string;
  price?: number;
  category?: Category;
  gender?: boolean;
  color?: string;
  sneakerDetailList: SneakerDetailList;
}
