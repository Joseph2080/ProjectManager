package org.chitsa.projectmanagement.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chitsa.projectmanagement.model.Milestone;
import org.chitsa.projectmanagement.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneDto {
    @NotEmpty
    @Size(min = 5, max = 30)
    String milestoneTitle;
    @NotEmpty
    @Size(min = 5, max = 50)
    String description;

    @NotEmpty
    String deadline;

    @NotEmpty
    double fee;

    public static Milestone convertToMilestone(MilestoneDto milestoneDto) {
        Milestone milestone = new Milestone();
        milestone.setMilestoneTitle(milestoneDto.getMilestoneTitle());
        milestone.setDeadline(User.convertStringToDate(milestoneDto.getDeadline()));
        milestone.setFee(milestone.getFee());
        milestone.setDescription(milestone.getDescription());
        return milestone;
    }

    public static MilestoneDto convertMilestoneToDto(Milestone milestone) {
        MilestoneDto milestoneDto = new MilestoneDto();
        milestoneDto.setMilestoneTitle(milestone.getMilestoneTitle());
        milestoneDto.setDeadline(milestone.getDeadline().toString());
        milestoneDto.setFee(milestone.getFee());
        milestoneDto.setDescription(milestone.getDescription());
        return milestoneDto;
    }

}
