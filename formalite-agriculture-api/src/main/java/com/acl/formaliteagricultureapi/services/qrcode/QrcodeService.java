package com.acl.formaliteagricultureapi.services.qrcode;

import com.acl.formaliteagricultureapi.dto.atd.QrCodeDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Service
public interface QrcodeService {

    String generateQrCode(QrCodeDto request) throws IOException;
}
