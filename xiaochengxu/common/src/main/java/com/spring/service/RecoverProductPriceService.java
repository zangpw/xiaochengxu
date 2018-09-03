package com.spring.service;

import com.spring.model.OrderProduct;
import com.spring.model.RecoverProductPrice;

public interface RecoverProductPriceService extends BaseService<RecoverProductPrice>{
	public RecoverProductPrice getPriceByPropertyDetailId(Long[] detailIds,Long memberId);
}
