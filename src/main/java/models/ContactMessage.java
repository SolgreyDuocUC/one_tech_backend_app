package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.OffsetDateTime;
@Entity
@Table(name = "CONTACT_MESSAGES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Long id;

    @Column(name = "name_message", length = 120, nullable = false)
    @NotBlank
    private String nameMessage;

    @Column(name = "email_message", length = 120, nullable = false)
    @Email @NotBlank
    private String email;

    @Column(name = "phone_message",length = 20)
    private String phone;

    @Column(name = "message_message", length = 1000, nullable = false)
    @NotBlank
    private String message;

    @Column(name = "via_message", length = 30)
    private String via;

    @Column(name = "status_message", length = 30)
    private String status;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_users")
    @JsonBackReference("user-messages")
    private User user;
}
