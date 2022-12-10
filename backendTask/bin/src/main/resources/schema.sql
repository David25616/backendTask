DROP TABLE IF EXISTS THRYVE_DATA;
CREATE TABLE THRYVE_DATA(
    id IDENTITY primary key,
    start_timestamp TIMESTAMP NOT NULL,
    end_timestamp TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    dynamic_value_type INT NOT NULL,
    value INT NOT NULL,
    user_id INT NOT NULL
);

DROP TABLE IF EXISTS THRVYE_EVENT;
CREATE TABLE THRYVE_EVENT(
    id IDENTITY primary key,
    action VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    created TIMESTAMP NOT NULL
);