import { Cars } from './cars.model';

describe('Cars', () => {
  it('should create an instance', () => {
    // @ts-ignore
    expect(new Cars()).toBeTruthy();
  });
});
