ALTER TABLE users ADD COLUMN avatar_url VARCHAR(500);
ALTER TABLE users ADD COLUMN phone VARCHAR(40);
ALTER TABLE users ADD COLUMN bio VARCHAR(1000);

ALTER TABLE organizations ADD COLUMN logo_url VARCHAR(500);
ALTER TABLE organizations ADD COLUMN website VARCHAR(255);
ALTER TABLE organizations ADD COLUMN phone VARCHAR(40);
ALTER TABLE organizations ADD COLUMN email VARCHAR(255);
ALTER TABLE organizations ADD COLUMN address VARCHAR(500);
ALTER TABLE organizations ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';

CREATE TABLE teams (
    id BIGSERIAL PRIMARY KEY,
    organization_id BIGINT NOT NULL REFERENCES organizations(id) ON DELETE CASCADE,
    name VARCHAR(120) NOT NULL,
    description VARCHAR(500),
    lead_member_id BIGINT,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_team_name_per_organization UNIQUE (organization_id, name)
);
CREATE INDEX idx_teams_organization ON teams(organization_id);

ALTER TABLE positions ADD COLUMN level INTEGER;
ALTER TABLE positions ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';

ALTER TABLE members ADD COLUMN user_id BIGINT REFERENCES users(id) ON DELETE SET NULL;
ALTER TABLE members ADD COLUMN access_role VARCHAR(20) NOT NULL DEFAULT 'MEMBER';
ALTER TABLE members ADD COLUMN position_id BIGINT REFERENCES positions(id) ON DELETE SET NULL;
ALTER TABLE members ADD COLUMN team_id BIGINT REFERENCES teams(id) ON DELETE SET NULL;
ALTER TABLE members ADD COLUMN manager_id BIGINT REFERENCES members(id) ON DELETE SET NULL;
ALTER TABLE members ADD COLUMN joined_at DATE;
ALTER TABLE members ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';
ALTER TABLE members ADD COLUMN location VARCHAR(120);
ALTER TABLE members ADD COLUMN phone VARCHAR(40);
ALTER TABLE members ADD COLUMN bio VARCHAR(1000);
ALTER TABLE members ADD COLUMN avatar_url VARCHAR(500);
ALTER TABLE members ADD CONSTRAINT uk_member_user_per_organization UNIQUE (organization_id, user_id);
ALTER TABLE teams ADD CONSTRAINT fk_teams_lead_member FOREIGN KEY (lead_member_id) REFERENCES members(id) ON DELETE SET NULL;
CREATE INDEX idx_members_user ON members(user_id);
CREATE INDEX idx_members_team ON members(team_id);
CREATE INDEX idx_members_position ON members(position_id);
CREATE INDEX idx_members_manager ON members(manager_id);

INSERT INTO members (name, email, organization_id, user_id, access_role, joined_at, status, created_at, updated_at)
SELECT u.name, u.email, o.id, u.id, 'OWNER', CURRENT_DATE, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM organizations o JOIN users u ON u.id = o.owner_id
WHERE NOT EXISTS (SELECT 1 FROM members m WHERE m.organization_id = o.id AND m.user_id = u.id);

CREATE TABLE refresh_tokens (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    token_hash VARCHAR(64) NOT NULL UNIQUE,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    revoked_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_refresh_tokens_user ON refresh_tokens(user_id);

CREATE TABLE password_reset_tokens (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    token_hash VARCHAR(64) NOT NULL UNIQUE,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    used_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE invitations (
    id BIGSERIAL PRIMARY KEY,
    organization_id BIGINT NOT NULL REFERENCES organizations(id) ON DELETE CASCADE,
    email VARCHAR(255) NOT NULL,
    access_role VARCHAR(20) NOT NULL,
    token_hash VARCHAR(64) NOT NULL UNIQUE,
    invited_by_id BIGINT NOT NULL REFERENCES users(id),
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    accepted_at TIMESTAMP WITH TIME ZONE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_invitations_organization ON invitations(organization_id);
CREATE INDEX idx_invitations_email ON invitations(email);

CREATE TABLE activity_logs (
    id BIGSERIAL PRIMARY KEY,
    organization_id BIGINT NOT NULL REFERENCES organizations(id) ON DELETE CASCADE,
    actor_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    action VARCHAR(60) NOT NULL,
    target_type VARCHAR(60),
    target_id VARCHAR(80),
    metadata TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_activity_organization_created ON activity_logs(organization_id, created_at DESC);

CREATE TABLE notifications (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    organization_id BIGINT REFERENCES organizations(id) ON DELETE CASCADE,
    type VARCHAR(60) NOT NULL,
    title VARCHAR(180) NOT NULL,
    message VARCHAR(1000) NOT NULL,
    link VARCHAR(500),
    read_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_notifications_user_created ON notifications(user_id, created_at DESC);
