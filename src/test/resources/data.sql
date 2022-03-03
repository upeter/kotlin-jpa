insert into client values (1, 'Very Big Client, Inc.') ON CONFLICT DO NOTHING;
select setval('client_id_seq', 2);

insert into project values (1, 'Test Project', 1) ON CONFLICT DO NOTHING;
insert into project values (2, 'Another Test Project', 1) ON CONFLICT DO NOTHING;
select setval('project_id_seq', 3) ;

insert into contact values ('John', 'john@verybigclient.com', 1) ON CONFLICT DO NOTHING;
insert into contact values ('Melissa', 'melissa@verybigclient.com', 1) ON CONFLICT DO NOTHING;
--select setval('contact_id_seq', 3) ;




