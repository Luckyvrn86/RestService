CREATE TABLE `university`.`faculty` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
INSERT INTO `university`.`faculty` (`id`, `name`)
    VALUES ('1', 'Технический'), ('2', 'Биологический'), ('3', 'Гуманитарный');
CREATE TABLE `university`.`student` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `faculty` INT NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
INSERT INTO `university`.`student` (`id`, `name`, `faculty`)
    VALUES ('1', 'Иванов Иван', '1'), ('2', 'Петр Петров', '1'),
            ('3', 'Сергей Сергеев', '2'), ('4', 'Василий Васильев', '2'),
            ('5', 'Света Светикова', '3'), ('6', 'Анна Каренина', '3');
CREATE TABLE `university`.`teacher` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
INSERT INTO `university`.`teacher` (`id`, `name`)
    VALUES ('1', 'Иван Иванович'), ('2', 'Петр Петрович'), ('3', 'Степан Степанович'),
            ('4', 'Илья Ильич'), ('5', 'Сергей Сергеевич'), ('6', 'Василий Васильевич');
CREATE TABLE `university`.`student_teacher` (
    `student_id` INT NOT NULL,
    `teacher_id` INT NOT NULL,
    PRIMARY KEY (`student_id`, `teacher_id`),
    INDEX `fk_teacher_id_idx` (`teacher_id` ASC) VISIBLE,
    CONSTRAINT `fk_student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `university`.`student` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_teacher_id`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `university`.`teacher` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO `university`.`student_teacher` (`student_id`, `teacher_id`)
VALUES ('1', '1'), ('3', '1'), ('1', '2'), ('5', '2'), ('2', '3'), ('3', '3'),
       ('5', '4'), ('2', '5'), ('4', '5'), ('6', '5'), ('4', '6'), ('6', '6');