package com.cfs.BMS.repository;

import com.cfs.BMS.entity.Booking;
import com.cfs.BMS.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

  List<Booking> findByUserId(Long userId);

  List<Booking> findByShowId(Long showId);

  // Find all seat ids that are already booked for given show
  @Query("SELECT s.id FROM Booking b JOIN b.seats s WHERE b.show.id=:showId AND b.status='CONFIRMED'")
  List<Long> findBookedSeatIdsByShowId(@Param("showId") Long showId);

  // Mark a confirmed ticket as used after successful QR verification
  @Modifying
  @Query("""
        UPDATE Booking b
        SET b.status = :usedStatus
        WHERE b.id = :id
          AND b.status = :confirmedStatus
    """)
  int markBookingAsUsed(
          @Param("id") Long id,
          @Param("usedStatus") BookingStatus usedStatus,
          @Param("confirmedStatus") BookingStatus confirmedStatus
  );
}