package br.com.mercadolivre.novoproduto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {
    Set<String> enviaImagem(List<MultipartFile> imagens);
}
