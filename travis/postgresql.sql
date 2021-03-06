CREATE USER basic_application_test WITH PASSWORD 'basic_application_test';
CREATE USER owsicore_test WITH PASSWORD 'owsicore_test';
CREATE USER owsiexample_test WITH PASSWORD 'owsiexample_test';

CREATE DATABASE basic_application_test WITH OWNER basic_application_test;
CREATE DATABASE owsicore_test WITH OWNER owsicore_test;
CREATE DATABASE owsiexample_test WITH OWNER owsiexample_test;

\c owsicore_test
CREATE SCHEMA owsicore AUTHORIZATION owsicore_test;

\c basic_application_test
CREATE SCHEMA basic_application_test AUTHORIZATION basic_application_test;
