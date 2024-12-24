import { Person } from './person.model';

describe('Person', () => {
  it('should create an instance', () => {
    // @ts-ignore
    expect(new Person()).toBeTruthy();
  });
});
