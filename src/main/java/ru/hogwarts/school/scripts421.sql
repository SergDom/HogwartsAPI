ALTER TABLE student ADD CONSTRAINT ageCondition CHECK (age >16);
ALTER TABLE student ALTER COLUMN age SET NOT NULL;
ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;
ALTER TABLE student ADD CONSTRAINT nameCondition UNIQUE (name);

ALTER TABLE faculty ADD CONSTRAINT name_color_unique UNIQUE (name, color);