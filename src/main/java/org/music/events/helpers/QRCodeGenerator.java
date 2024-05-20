package org.music.events.helpers;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Hulpmiddel voor het genereren van QR-codes
public class QRCodeGenerator {
    // Methode om een QR-code afbeelding te genereren
    public static byte[] generateQRCodeImage(String qrCodeId, int width, int height) throws WriterException, IOException {
        // Map met coderingsopties instellen
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // QRCodeWriter instantiëren
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // BitMatrix genereren met de tekst, breedte, hoogte en coderingsopties
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeId, BarcodeFormat.QR_CODE, width, height, hints);
        // ByteArrayOutputStream instantiëren om de afbeelding op te slaan
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Afbeelding naar outputStream schrijven
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        // ByteArray van outputStream retourneren
        return outputStream.toByteArray();
    }

}