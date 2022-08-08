import { Comment } from 'src/app/comment';
import { Files } from './Files';
export interface Innovation{
     innovationId: string | null;
     innovationName: string;
     innovatorId : string | null;
     name: string | null;
     email: string | null;
     innovationDesc: string | null;
     challenges: string | null;
     domain: string | null;
     status: string | null;
     rating: number | null;
    // List<String> stakeholders;
     dateOfCreation: Date | null;
    comments: Comment[],
    coverPhoto: Files,
    uploadedFiles: Files[]
}