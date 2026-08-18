package com.sneha.payment.service;

import com.sneha.payment.dto.MerchantDtos.CreateRequest;
import com.sneha.payment.entity.Merchant;
import com.sneha.payment.repository.MerchantRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

 class MerchantServiceTest {

     @Test
     void shouldCreateMerchant() {

         MerchantRepository repo = mock(MerchantRepository.class);

         when(repo.existsByEmail("shop@example.com"))
                 .thenReturn(false);

         Merchant savedMerchant = new Merchant();
         savedMerchant.setId(1L);
         savedMerchant.setMerchantCode("MER-TEST123");
         savedMerchant.setBusinessName("sneha Shop");
         savedMerchant.setEmail("shop@example.com");
         savedMerchant.setPhone("9876543210");

         when(repo.save(any(Merchant.class)))
                 .thenReturn(savedMerchant);

         var service = new MerchantService(repo);

         var response = service.create(
                 new CreateRequest(
                         "sneha Shop",
                         "shop@example.com",
                         "9876543210"
                 )
         );

         assertThat(response).isNotNull();

         assertThat(response.businessName())
                 .isEqualTo("sneha Shop");

         assertThat(response.merchantCode())
                 .startsWith("MER-");

         verify(repo).save(any(Merchant.class));
     }
 }




