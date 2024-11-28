package effectiveMobile.com.taskManagementSystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Comment
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
@SequenceGenerator(name = "default_generator", sequenceName = "comments_sequence", allocationSize = 1)
public class Comment extends GenericModel {

	@Column(name = "description", nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENT_TASK"))
	private Task task;
}