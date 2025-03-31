package com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper;

import com.hackaton.msvc_user.domain.util.Rank;
import com.hackaton.msvc_user.domain.util.Status;
import org.mapstruct.Named;
import java.util.Arrays;

import org.mapstruct.Named;
import java.util.Arrays;

public class StatusRankMapper {

    // Convierte un String a un objeto de tipo Status
    @Named("stringToStatus")
    public static Status stringToStatus(String status) {
        return Arrays.stream(Status.values())  // Convierte el array de valores de Status en un Stream
                .filter(s -> s.getStatusName().equalsIgnoreCase(status)) // Filtra los que coinciden con el nombre del status (ignorando mayúsculas/minúsculas)
                .findFirst() // Toma el primer resultado encontrado
                .orElse(null); // Si no encuentra coincidencias, retorna null
    }

    // Convierte un objeto de tipo Status a String
    @Named("statusToString")
    public static String statusToString(Status status) {
        return status != null ? status.getStatusName() : null; // Si el status no es nulo, devuelve su nombre, si es nulo, devuelve null
    }

    // Convierte un String a un objeto de tipo Rank
    @Named("stringToRank")
    public static Rank stringToRank(String rank) {
        return Arrays.stream(Rank.values())  // Convierte el array de valores de Rank en un Stream
                .filter(r -> r.getRankName().equalsIgnoreCase(rank)) // Filtra los que coinciden con el nombre del rank (ignorando mayúsculas/minúsculas)
                .findFirst() // Toma el primer resultado encontrado
                .orElse(null); // Si no encuentra coincidencias, retorna null
    }

    // Convierte un objeto de tipo Rank a String
    @Named("rankToString")
    public static String rankToString(Rank rank) {
        return rank != null ? rank.getRankName() : null; // Si el rank no es nulo, devuelve su nombre, si es nulo, devuelve null
    }
}

