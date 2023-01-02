package sof.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "store", schema = "movie")
@Getter
@Setter
public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id", nullable = false)
    private Byte storeId;

    @OneToOne
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff managerStaffId;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address addressId;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
