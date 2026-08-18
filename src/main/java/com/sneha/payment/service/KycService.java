package com.sneha.payment.service;

import com.sneha.payment.dto.KycDtos.*;
import com.sneha.payment.entity.Kyc;
import com.sneha.payment.entity.KycStatus;
import com.sneha.payment.entity.Merchant;
import com.sneha.payment.exception.ApiException;
import com.sneha.payment.repository.KycRepository;
import com.sneha.payment.repository.MerchantRepository;
import org.springframework.stereotype.Service;


@Service
public class KycService {
    private final KycRepository kyc;
    private final MerchantRepository merchants;

    public KycService(KycRepository k, MerchantRepository m) {
        kyc = k;
        merchants = m;
    }

    public Response submit(CreateRequest r) {
        Merchant m = merchants.findById(r.merchantId()).orElseThrow(() -> new ApiException("Merchant not found"));
        if (kyc.findByMerchantId(m.getId()).isPresent()) throw new ApiException("KYC already submitted");
        Kyc x = new Kyc();
        x.setMerchant(m);
        x.setDocumentType(r.documentType());
        x.setDocumentNumber(r.documentNumber());
        return map(kyc.save(x));
    }

    public Response update(Long id, KycStatus status) {
        Kyc x = kyc.findById(id).orElseThrow(() -> new ApiException("KYC not found"));
        x.setStatus(status);
        return map(kyc.save(x));
    }

    private Response map(Kyc x) {
        return new Response(
               x.getId(),
                x.getMerchant().getId(),
                x.getDocumentType(),
                x.getDocumentNumber(),
                x.getStatus().name()
        );
    } {
    }
}
