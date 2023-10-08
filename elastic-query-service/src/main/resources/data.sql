CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


INSERT INTO public.users(
	id, username, firstname, lastname)
	VALUES ('aaa17a30-64b9-4ba9-841c-656fbbadc690', 'app_user', 'Standard', 'User');
INSERT INTO public.users(
	id, username, firstname, lastname)
	VALUES ('098c80dd-3bb5-4063-8609-31310af863c5', 'app_admin', 'Admin', 'User');
INSERT INTO public.users(
	id, username, firstname, lastname)
	VALUES ('6317c768-6c61-468e-9cf6-c483a3a93936', 'app_super_user', 'Super', 'User');


insert into documents(id, document_id)
values ('aaa17a30-64b9-4ba9-841c-656fbbadc691', 1710433798762938401);
insert into documents(id, document_id)
values ('098c80dd-3bb5-4063-8609-31310af863c2', 1710433815443939348);
insert into documents(id, document_id)
values ('6317c768-6c61-468e-9cf6-c483a3a93933', 1710433891125707219);

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(),'aaa17a30-64b9-4ba9-841c-656fbbadc690', 'aaa17a30-64b9-4ba9-841c-656fbbadc691', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(),'098c80dd-3bb5-4063-8609-31310af863c5', 'aaa17a30-64b9-4ba9-841c-656fbbadc691', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(),'098c80dd-3bb5-4063-8609-31310af863c5', '098c80dd-3bb5-4063-8609-31310af863c2', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), '098c80dd-3bb5-4063-8609-31310af863c5', '6317c768-6c61-468e-9cf6-c483a3a93933', 'READ');

insert into user_permissions(user_permission_id, user_id, document_id, permission_type)
values (uuid_generate_v4(), '6317c768-6c61-468e-9cf6-c483a3a93936', 'aaa17a30-64b9-4ba9-841c-656fbbadc691', 'READ');


