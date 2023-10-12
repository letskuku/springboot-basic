package com.programmers.vouchermanagement.voucher.application;

import com.programmers.vouchermanagement.voucher.domain.FixedAmountVoucher;
import com.programmers.vouchermanagement.voucher.domain.PercentDiscountVoucher;
import com.programmers.vouchermanagement.voucher.domain.VoucherType;
import com.programmers.vouchermanagement.voucher.dto.VoucherRequestDto;
import com.programmers.vouchermanagement.voucher.repository.VoucherRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public void createVoucher(VoucherRequestDto voucherRequestDto) {

        Long discount = voucherRequestDto.getDiscount();
        VoucherType voucherType = voucherRequestDto.getVoucherType();

        switch (voucherType) {

            case FIXED -> {

                FixedAmountVoucher.validateDiscount(discount);

                voucherRepository.save(new FixedAmountVoucher(UUID.randomUUID(), discount, voucherType));
            }

            case PERCENT -> {

                PercentDiscountVoucher.validateDiscount(discount);

                voucherRepository.save(new PercentDiscountVoucher(UUID.randomUUID(), discount, voucherType));
            }
        }
    }
}
