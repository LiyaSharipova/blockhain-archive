package com.github.liyasharipova.blockchain.archive.hashing.service;

/**
 * Сервис для работы с данными в блокчейне
 */
public interface BlockchainDataService {

    /**
     * Помещаем файл в транзакцию, далее в блок, потом в блокчейн
     * @param fileData данные файла
     * @param userId идентификатор пользователя
     * @param uploadDateTime время загрузки файла
     * @return boolean Успешно ли файл поместился в блокчейн. Влияет на дальнейшую
     */
    void placeToBlockchain(byte[] fileData, String userId, long uploadDateTime);
}