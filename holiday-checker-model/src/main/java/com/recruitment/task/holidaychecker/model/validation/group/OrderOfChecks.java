package com.recruitment.task.holidaychecker.model.validation.group;

import javax.validation.GroupSequence;

@GroupSequence({ NullCheck.class, DuplicationCheck.class })
public interface OrderOfChecks {

}